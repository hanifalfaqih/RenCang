package id.hanifalfaqih.rencang

object ChiliPlantRepository {

    // Pastikan Anda sudah mengimpor ikon-ikon ini sebagai Vector Asset
    // dengan nama ic_watering_can, ic_observe, ic_fertilizer
    private val tasks = listOf(
        DailyTask(1, "Penyemaian", "Semai biji cabai di media tanam yang lembab.", R.drawable.ic_observe),
        DailyTask(2, "Penyiraman Awal", "Siram sedikit untuk menjaga kelembapan.", R.drawable.ic_watering_can),
        DailyTask(3, "Observasi Perkecambahan", "Perhatikan tanda-tanda perkecambahan.", R.drawable.ic_observe),
        DailyTask(4, "Penyiraman Rutin", "Siram tanaman secukupnya.", R.drawable.ic_watering_can),
        DailyTask(5, "Pemupukan Pertama", "Beri pupuk kompos sedikit.", R.drawable.ic_fertilizer),
        DailyTask(60, "Panen Raya", "Selamat! Waktunya panen cabai pertama Anda!", R.drawable.ic_journal) // Contoh task terakhir
        // Tambahkan task lain sesuai kebutuhan hingga totalDays
    )

    fun getTasks(): List<DailyTask> {
        return tasks
    }

    fun getTotalDays(): Int {
        // Penting: Sesuaikan ini dengan jumlah hari aktual dalam daftar tugas Anda
        // Untuk contoh ini, saya akan hardcode 60, tapi idealnya ini harus dinamis
        // atau pastikan list 'tasks' Anda benar-benar memiliki 60 entri jika totalDays = 60.
        // Jika tidak, error bisa terjadi saat mencoba mengakses task di hari terakhir.
        // Untuk sekarang, kita asumsikan list tasks di atas akan diperpanjang hingga 60 hari.
        return 60 // Atau tasks.map { it.day }.maxOrNull() ?: 0 atau tasks.size jika berurutan
    }
}
